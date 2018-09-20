package GastoDeputados;

/**
 *
 * @author ice
 */
public class Deputado {
    int bugged_date;
    String receipt_date;
    public int deputy_id;
    String political_party;
    String state_code;
    String name;
    String receipt_social_security_number;
    String receipt_descript;
    String establishiment_name;
    float receipt_value;

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
    
    
    
}
