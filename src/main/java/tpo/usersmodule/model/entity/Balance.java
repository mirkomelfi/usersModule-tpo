package tpo.usersmodule.model.entity;

public class Balance {
    float total_ingresos;
    float total_gastos;
    float balance_general;


    public Balance() {
    }

    public Balance(float total_ingresos, float total_gastos, float balance_general) {
        this.total_ingresos = total_ingresos;
        this.total_gastos = total_gastos;
        this.balance_general = balance_general;
    }

    public float getTotal_ingresos() {
        return total_ingresos;
    }

    public void setTotal_ingresos(float total_ingresos) {
        this.total_ingresos = total_ingresos;
    }

    public float getTotal_egresos() {
        return total_gastos;
    }

    public void setTotal_egresos(float total_gastos) {
        this.total_gastos = total_gastos;
    }

    public float getBalance_general() {
        return balance_general;
    }

    public void setBalance_general(float balance_general) {
        this.balance_general = balance_general;
    }
}
