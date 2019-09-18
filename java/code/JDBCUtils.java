import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
 
import javax.naming.spi.DirStateFactory.Result;
 
public class JDBCUtils {
        private static Statement st;
        private static String url;
        private static String driver;
        private static String user;
        private static String password;
         
        static{
                try {
                        //从jdbc.properties文件中获取数据初始化链接的值
                        Properties pro=new Properties();
                        InputStream is=JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
                        pro.load(is);
                        driver=pro.getProperty("driver");
                        url=pro.getProperty("url");
                        user=pro.getProperty("user");
                        password=pro.getProperty("password");        
                } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                }
        }
 
        // 创建连接
        private static Connection getConnection() throws ClassNotFoundException, SQLException {
                // TODO Auto-generated method stub
                Class.forName(driver);
                return DriverManager.getConnection(url,user,password);
        }
        //获取statement对象
        private static Statement createStatement(Connection con) throws SQLException{
                // TODO Auto-generated method stub
                return con.createStatement();
        }
        //获取preparedStatement对象
        public static PreparedStatement preparedStatement(Connection con,String sql) throws SQLException{
                return con.prepareStatement(sql);
        }
        // 执行一条静态SQL命令[insert/update/delete]
        public static int execUpdates(String sql) throws SQLException, ClassNotFoundException {
                Connection con = getConnection();
                Statement st = createStatement(con);
                int iCount = st.executeUpdate(sql);
                closeConnection(con, st, null, null);
                return iCount;
        }
        // 执行多条静态SQL命令[insert/update/delete]
                public static int[] execUpdates(String[] sql) throws SQLException, ClassNotFoundException {
                        Connection con = getConnection();
                        Statement st = createStatement(con);
                        for (int i = 0; i < sql.length; i++) {
                                st.addBatch(sql[i]);
                        }
                         
                        int[] iCount = st.executeBatch();
                        closeConnection(con, st, null, null);
                        return iCount;
                }
        // 执行一条动态的SQL命令[insert/update/delete]
        public static int execUpdate(String sql,Object[] params) throws SQLException, ClassNotFoundException {
                Connection con = getConnection();
                PreparedStatement pst = preparedStatement(con, sql);
                for (int i = 1; i<=params.length; i++) {
                        pst.setObject(i, params[i - 1]);
                }
                int iCount=pst.executeUpdate();
                closeConnection(con, null, pst, null);
                return iCount;
        }
 
        // 执行一条静态SQL命令[select]
        public static <T> List<T> execQuery(String sql, Class classs)
                        throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
                Connection con = getConnection();
                Statement st = createStatement(con);
                List<T> list = new ArrayList<T>();
                ResultSet rs = st.executeQuery(sql);
                ResultSetMetaData rsmd = rs.getMetaData();
                Field[] fields = classs.getDeclaredFields();
                if (rs != null) {
                        while (rs.next()) {
                                T t = (T) classs.newInstance();
                                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                                        String colName = rsmd.getColumnName(i);
                                        for (int j = 0; j < fields.length; j++) {
                                                String fieldName = fields[j].getName();
                                                if (colName.equals(fieldName)) {
                                                        // 赋值
                                                        boolean iRet = fields[j].isAccessible();
                                                        fields[j].setAccessible(true);
                                                        fields[j].set(t, rs.getObject(i));
                                                        fields[j].setAccessible(iRet);
                                                } else {
                                                        continue;
                                                }
                                        }
 
                                }
                                list.add(t);
                        }
                }
                closeConnection(con, st, null, rs);
                return list;
        }
 
        // 执行一条动态SQL命令[select]
        public static <T>List<T> execQuery(String sql,Object[] obj,Class classs ) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException{
                Connection con=getConnection();
                PreparedStatement pst=preparedStatement(con, sql);
                for (int i = 1; i <= obj.length; i++) {
                        pst.setObject(i, obj[i-1]);
                        System.out.println(obj[i-1]);
                }
                List<T> list=new ArrayList<T>();
                ResultSet rs=pst.executeQuery();
                ResultSetMetaData rsmd=rs.getMetaData();
                Field[] fields=classs.getDeclaredFields();
                if(rs!=null){
                        while(rs.next()){
                                T t=(T)classs.newInstance();
                                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                                        String        colName=rsmd.getColumnName(i);
                                        for (int j = 0; j < fields.length; j++) {
                                                String fieldName=fields[j].getName();
                                                if(colName.equals(fieldName)){
                                                        Boolean iRet=fields[j].isAccessible();
                                                        fields[j].setAccessible(true);
                                                        fields[j].set(t, rs.getObject(i));
                                                        fields[j].setAccessible(iRet);
                                                }else
                                                        continue;                                             
                                        }
                                }
                                list.add(t);
                        }
                }
                closeConnection(con, pst, null, rs);
                return list;
        }
        // 关闭连接，释放资源
        public static void closeConnection(Connection con, Statement st, PreparedStatement pst, ResultSet rs)
                        throws SQLException {
                if (rs != null) 
                        rs.close();                
                if (pst != null) 
                        pst.close();                
                if (st != null) 
                        st.close();                
                if (con != null) 
                        con.close();                
        }
}
