package com.woqiyounai.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author lxllxl233
 * @since 2020-10-02
 * 封装了数据库表的基本信息
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("COLUMNS")
public class Columns implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("TABLE_CATALOG")
    private String tableCatalog;

    @TableField("TABLE_SCHEMA")
    private String tableSchema;

    @TableField("TABLE_NAME")
    private String tableName;

    @TableField("COLUMN_NAME")
    private String columnName;

    @TableField("ORDINAL_POSITION")
    private Long ordinalPosition;

    @TableField("COLUMN_DEFAULT")
    private String columnDefault;

    @TableField("IS_NULLABLE")
    private String isNullable;

    @TableField("DATA_TYPE")
    private String dataType;

    @TableField("CHARACTER_MAXIMUM_LENGTH")
    private Long characterMaximumLength;

    @TableField("CHARACTER_OCTET_LENGTH")
    private Long characterOctetLength;

    @TableField("NUMERIC_PRECISION")
    private Long numericPrecision;

    @TableField("NUMERIC_SCALE")
    private Long numericScale;

    @TableField("DATETIME_PRECISION")
    private Long datetimePrecision;

    @TableField("CHARACTER_SET_NAME")
    private String characterSetName;

    @TableField("COLLATION_NAME")
    private String collationName;

    @TableField("COLUMN_TYPE")
    private String columnType;

    @TableField("COLUMN_KEY")
    private String columnKey;

    @TableField("EXTRA")
    private String extra;

    @TableField("PRIVILEGES")
    private String privileges;

    @TableField("COLUMN_COMMENT")
    private String columnComment;

    @TableField("GENERATION_EXPRESSION")
    private String generationExpression;

}
