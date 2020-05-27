public interface Iterator {
  /**
    * 是否存在下一个元素
    */
  boolean hasNext();
  /**
    * 获取下一个元素
    */
  Object next();
}
