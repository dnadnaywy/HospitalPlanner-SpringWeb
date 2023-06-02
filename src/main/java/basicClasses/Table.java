package basicClasses;

public class Table {
    public String tableName;
    public int nrOfRows;
    public String nested;

    public Table() {}

    public Table (String name, int nrOfRows, String nested) {
        this.tableName = name;
        this.nrOfRows = nrOfRows;
        this.nested = nested;
    }

    @Override
    public String toString() {
        return "Table{" +
                "tableName='" + tableName + '\'' +
                ", nrOfRows=" + nrOfRows + '\'' +
                ", nested=" + nested +
                '}';
    }

    public int getNrOfRows() {
        return nrOfRows;
    }

    public String getTableName() {
        return tableName;
    }

    public String getNested() {
        return nested;
    }

    public void setNrOfRows(int nrOfRows) {
        this.nrOfRows = nrOfRows;
    }

    public void setNested(String nested) {
        this.nested = nested;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
