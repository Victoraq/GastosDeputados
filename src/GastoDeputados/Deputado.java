package GastoDeputados;

/**
 *
 * @author ice
 */
public class Deputado {
    private int bugged_date;
    private String receipt_date;
    private int deputy_id;
    private String political_party;
    private String state_code;
    private String name;
    private String receipt_social_security_number;
    private String receipt_descript;
    private String establishiment_name;
    private float receipt_value;

    public Deputado(int bugged_date, String receipt_date, int deputy_id, String political_party, String state_code, String name, String receipt_social_security_number, String receipt_descript, String establishiment_name, float receipt_value) {
        this.bugged_date = bugged_date;
        this.receipt_date = receipt_date;
        this.deputy_id = deputy_id;
        this.political_party = political_party;
        this.state_code = state_code;
        this.name = name;
        this.receipt_social_security_number = receipt_social_security_number;
        this.receipt_descript = receipt_descript;
        this.establishiment_name = establishiment_name;
        this.receipt_value = receipt_value;
    }

    public int getBugged_date() {
        return bugged_date;
    }

    public String getReceipt_date() {
        return receipt_date;
    }

    public int getDeputy_id() {
        return deputy_id;
    }

    public String getPolitical_party() {
        return political_party;
    }

    public String getState_code() {
        return state_code;
    }

    public String getName() {
        return name;
    }

    public String getReceipt_social_security_number() {
        return receipt_social_security_number;
    }

    public String getReceipt_descript() {
        return receipt_descript;
    }

    public String getEstablishiment_name() {
        return establishiment_name;
    }

    public float getReceipt_value() {
        return receipt_value;
    }
    
    
    
}
