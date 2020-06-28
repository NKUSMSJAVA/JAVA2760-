package sample;

public class DataStore {
    private String cNum="Dafalute";
    private String c="Dafalute";
    private String AeqNum="Dafalute";
    private String Aeq="Dafalute";
    private String beqNum="Dafalute";
    private String beq="Dafalute";
    private String AleqNum="Dafalute";
    private String Aleq="Dafalute";
    private String bleqNum="Dafalute";
    private String bleq="Dafalute";
    private String lindexNum="Dafalute";
    private String lindex="Dafalute";
    private String nindexNum="Dafalute";
    private String nindex="Dafalute";

    public DataStore() {
    }

    public DataStore(String cNum, String c, String aeqNum, String aeq, String beqNum, String beq, String aleqNum, String aleq, String bleqNum, String bleq, String lindexNum, String lindex, String nindexNum, String nindex) {
        this.cNum = cNum;
        this.c = c;
        AeqNum = aeqNum;
        Aeq = aeq;
        this.beqNum = beqNum;
        this.beq = beq;
        AleqNum = aleqNum;
        Aleq = aleq;
        this.bleqNum = bleqNum;
        this.bleq = bleq;
        this.lindexNum = lindexNum;
        this.lindex = lindex;
        this.nindexNum = nindexNum;
        this.nindex = nindex;
    }

    public String getcNum() {
        return cNum;
    }

    public void setcNum(String cNum) {
        this.cNum = cNum;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getAeqNum() {
        return AeqNum;
    }

    public void setAeqNum(String aeqNum) {
        AeqNum = aeqNum;
    }

    public String getAeq() {
        return Aeq;
    }

    public void setAeq(String aeq) {
        Aeq = aeq;
    }

    public String getBeqNum() {
        return beqNum;
    }

    public void setBeqNum(String beqNum) {
        this.beqNum = beqNum;
    }

    public String getBeq() {
        return beq;
    }

    public void setBeq(String beq) {
        this.beq = beq;
    }

    public String getAleqNum() {
        return AleqNum;
    }

    public void setAleqNum(String aleqNum) {
        AleqNum = aleqNum;
    }

    public String getAleq() {
        return Aleq;
    }

    public void setAleq(String aleq) {
        Aleq = aleq;
    }

    public String getBleqNum() {
        return bleqNum;
    }

    public void setBleqNum(String bleqNum) {
        this.bleqNum = bleqNum;
    }

    public String getBleq() {
        return bleq;
    }

    public void setBleq(String bleq) {
        this.bleq = bleq;
    }

    public String getLindexNum() {
        return lindexNum;
    }

    public void setLindexNum(String lindexNum) {
        this.lindexNum = lindexNum;
    }

    public String getLindex() {
        return lindex;
    }

    public void setLindex(String lindex) {
        this.lindex = lindex;
    }

    public String getNindexNum() {
        return nindexNum;
    }

    public void setNindexNum(String nindexNum) {
        this.nindexNum = nindexNum;
    }

    public String getNindex() {
        return nindex;
    }

    public void setNindex(String nindex) {
        this.nindex = nindex;
    }
}
