package org.xine.qtime.dal.dialect;

import java.sql.Types;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.dialect.function.VarArgsSQLFunction;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;

/**
 * The Class SQLiteDialect.
 */
public class SQLiteDialect extends Dialect {

    /**
     * Instantiates a new SQ lite dialect.
     */
    public SQLiteDialect() {
        super();
        registerColumnType(Types.BIT, "integer");
        registerColumnType(Types.TINYINT, "tinyint");
        registerColumnType(Types.SMALLINT, "smallint");
        registerColumnType(Types.INTEGER, "integer");
        registerColumnType(Types.BIGINT, "bigint");
        registerColumnType(Types.FLOAT, "float");
        registerColumnType(Types.REAL, "real");
        registerColumnType(Types.DOUBLE, "double");
        registerColumnType(Types.NUMERIC, "numeric");
        registerColumnType(Types.DECIMAL, "decimal");
        registerColumnType(Types.CHAR, "char");
        registerColumnType(Types.VARCHAR, "varchar");
        registerColumnType(Types.LONGVARCHAR, "longvarchar");
        registerColumnType(Types.DATE, "date");
        registerColumnType(Types.TIME, "time");
        registerColumnType(Types.TIMESTAMP, "timestamp");
        registerColumnType(Types.BINARY, "blob");
        registerColumnType(Types.VARBINARY, "blob");
        registerColumnType(Types.LONGVARBINARY, "blob");
        // registerColumnType(Types.NULL, "null");
        registerColumnType(Types.BLOB, "blob");
        registerColumnType(Types.CLOB, "clob");
        registerColumnType(Types.BOOLEAN, "integer");

        registerFunction("concat", new VarArgsSQLFunction(new StringType(), "", "||", ""));
        registerFunction("mod", new SQLFunctionTemplate(new IntegerType(), "?1 % ?2"));
        registerFunction("substr", new StandardSQLFunction("substr", new StringType()));
        registerFunction("substring", new StandardSQLFunction("substr", new StringType()));
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.dialect.Dialect#supportsIdentityColumns()
     */
    @Override
    public boolean supportsIdentityColumns() {
        return true;
    }

    /*
     * public boolean supportsInsertSelectIdentity() {
     * return true; // As specify in NHibernate dialect
     * }
     */

    /*
     * (non-Javadoc)
     * @see org.hibernate.dialect.Dialect#hasDataTypeInIdentityColumn()
     */
    @Override
    public boolean hasDataTypeInIdentityColumn() {
        return false; // As specify in NHibernate dialect
    }

    /*
     * public String appendIdentitySelectToInsert(String insertString) {
     * return new StringBuffer(insertString.length()+30). // As specify in NHibernate dialect
     * append(insertString).
     * append("; ").append(getIdentitySelectString()).
     * toString();
     * }
     */

    /*
     * (non-Javadoc)
     * @see org.hibernate.dialect.Dialect#getIdentityColumnString()
     */
    @Override
    public String getIdentityColumnString() {
        // return "integer primary key autoincrement";
        return "integer";
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.dialect.Dialect#getIdentitySelectString()
     */
    @Override
    public String getIdentitySelectString() {
        return "select last_insert_rowid()";
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.dialect.Dialect#supportsLimit()
     */
    @Override
    public boolean supportsLimit() {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.dialect.Dialect#getLimitString(java.lang.String, boolean)
     */
    @Override
    public String getLimitString(final String query, final boolean hasOffset) {
        return new StringBuffer(query.length() + 20).append(query).append(hasOffset ? " limit ? offset ?" : " limit ?").toString();
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.dialect.Dialect#supportsTemporaryTables()
     */
    @Override
    public boolean supportsTemporaryTables() {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.dialect.Dialect#getCreateTemporaryTableString()
     */
    @Override
    public String getCreateTemporaryTableString() {
        return "create temporary table if not exists";
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.dialect.Dialect#dropTemporaryTableAfterUse()
     */
    @Override
    public boolean dropTemporaryTableAfterUse() {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.dialect.Dialect#supportsCurrentTimestampSelection()
     */
    @Override
    public boolean supportsCurrentTimestampSelection() {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.dialect.Dialect#isCurrentTimestampSelectStringCallable()
     */
    @Override
    public boolean isCurrentTimestampSelectStringCallable() {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.dialect.Dialect#getCurrentTimestampSelectString()
     */
    @Override
    public String getCurrentTimestampSelectString() {
        return "select current_timestamp";
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.dialect.Dialect#supportsUnionAll()
     */
    @Override
    public boolean supportsUnionAll() {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.dialect.Dialect#hasAlterTable()
     */
    @Override
    public boolean hasAlterTable() {
        return false; // As specify in NHibernate dialect
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.dialect.Dialect#dropConstraints()
     */
    @Override
    public boolean dropConstraints() {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.dialect.Dialect#getAddColumnString()
     */
    @Override
    public String getAddColumnString() {
        return "add column";
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.dialect.Dialect#getForUpdateString()
     */
    @Override
    public String getForUpdateString() {
        return "";
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.dialect.Dialect#supportsOuterJoinForUpdate()
     */
    @Override
    public boolean supportsOuterJoinForUpdate() {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.dialect.Dialect#getDropForeignKeyString()
     */
    @Override
    public String getDropForeignKeyString() {
        throw new UnsupportedOperationException("No drop foreign key syntax supported by SQLiteDialect");
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.dialect.Dialect#getAddForeignKeyConstraintString(java.lang.String, java.lang.String[],
     * java.lang.String, java.lang.String[], boolean)
     */
    @Override
    public String getAddForeignKeyConstraintString(final String constraintName, final String[] foreignKey, final String referencedTable, final String[] primaryKey, final boolean referencesPrimaryKey) {
        throw new UnsupportedOperationException("No add foreign key syntax supported by SQLiteDialect");
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.dialect.Dialect#getAddPrimaryKeyConstraintString(java.lang.String)
     */
    @Override
    public String getAddPrimaryKeyConstraintString(final String constraintName) {
        throw new UnsupportedOperationException("No add primary key syntax supported by SQLiteDialect");
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.dialect.Dialect#supportsIfExistsBeforeTableName()
     */
    @Override
    public boolean supportsIfExistsBeforeTableName() {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see org.hibernate.dialect.Dialect#supportsCascadeDelete()
     */
    @Override
    public boolean supportsCascadeDelete() {
        return false;
    }

}
