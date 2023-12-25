package cn.huanzi.qch.baseadmin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.Properties;

/**
 * 自定义注释生成器
 *
 */
public class CommentGenerator extends DefaultCommentGenerator {
    private boolean addCommentRemark = false;
    private static final String EXAMPLE_SUFFIX="Example";
    //private static final String API_MODEL_PROPERTY_FULL_CLASS_NAME="io.swagger.annotations.ApiModelProperty";

    /**
     *  增加备注注释
     */
    @Override
    public void addConfigurationProperties(Properties properties) {
        super.addConfigurationProperties(properties);
        this.addCommentRemark = StringUtility.isTrue(properties.getProperty("addRemarkComments"));
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        String remarks = introspectedColumn.getRemarks();
        if(addCommentRemark && StringUtility.stringHasValue(remarks)) {
            //this.addFieldJavaDoc(field, remarks);

            //数据库中特殊字符需要转义
            if(remarks.contains("\"")){
                remarks = remarks.replace("\"","'");
            }
            //给model的字段添加swagger注解
            //field.addJavaDocLine("@ApiModelProperty(value = \""+remarks+"\")");
        }
    }


    private void addFieldJavaDoc(Field field, String remarks) {
        field.addJavaDocLine("/**");
        String[] remarkLines = remarks.split(System.getProperty("line.separator"));
        for (String remarkLine : remarkLines) {
            field.addJavaDocLine(" * " + remarkLine);
        }
        addJavadocTag(field, false);
        field.addJavaDocLine(" */");
    }

//    @Override
//    public void addJavaFileComment(CompilationUnit compilationUnit) {
//        super.addJavaFileComment(compilationUnit);
//        //只在model中添加swagger注解类的导入
//        if(!compilationUnit.isJavaInterface()&&!compilationUnit.getType().getFullyQualifiedName().contains(EXAMPLE_SUFFIX)){
//            compilationUnit.addImportedType(new FullyQualifiedJavaType(API_MODEL_PROPERTY_FULL_CLASS_NAME));
//        }
//    }
}
