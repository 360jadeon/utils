/**
 * 标注参数、属性、方法不可为 Null
 */
@Target({ ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD })
public @interface NotNull {

}
