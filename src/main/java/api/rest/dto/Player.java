package api.rest.dto;

public class Player {

    private String id;
    private Account account;
    private String name;

    public Player(){
        super();
    }
    public Player(String id){
        super();
        this.id = id;
    }
    public Player(String id,Account account,String name){
        super();
        this.id = id;
        this.account = account;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", account=" + account.getAccountId() +
                ", name='" + name + '\'' +
                '}';
    }
}
