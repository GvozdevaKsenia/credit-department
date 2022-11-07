package com.gvozdeva.creditdepartment2.model.entity;

public class Bank {

    private Integer id;
    private String name;
    private String address;
    private String telephone;
    private String email;

    public Bank(Integer id, String name, String address, String telephone, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
    }

    public Bank() {
    }

    public static BankBuilder builder() {
        return new BankBuilder();
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Bank)) return false;
        final Bank other = (Bank) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$address = this.getAddress();
        final Object other$address = other.getAddress();
        if (this$address == null ? other$address != null : !this$address.equals(other$address)) return false;
        final Object this$telephone = this.getTelephone();
        final Object other$telephone = other.getTelephone();
        if (this$telephone == null ? other$telephone != null : !this$telephone.equals(other$telephone)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Bank;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $address = this.getAddress();
        result = result * PRIME + ($address == null ? 43 : $address.hashCode());
        final Object $telephone = this.getTelephone();
        result = result * PRIME + ($telephone == null ? 43 : $telephone.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        return result;
    }

    public String toString() {
        return "Bank(id=" + this.getId() + ", name=" + this.getName() + ", address=" + this.getAddress() + ", telephone=" + this.getTelephone() + ", email=" + this.getEmail() + ")";
    }

    public static class BankBuilder {
        private Integer id;
        private String name;
        private String address;
        private String telephone;
        private String email;

        BankBuilder() {
        }

        public BankBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public BankBuilder name(String name) {
            this.name = name;
            return this;
        }

        public BankBuilder address(String address) {
            this.address = address;
            return this;
        }

        public BankBuilder telephone(String telephone) {
            this.telephone = telephone;
            return this;
        }

        public BankBuilder email(String email) {
            this.email = email;
            return this;
        }

        public Bank build() {
            return new Bank(id, name, address, telephone, email);
        }

        public String toString() {
            return "Bank.BankBuilder(id=" + this.id + ", name=" + this.name + ", address=" + this.address + ", telephone=" + this.telephone + ", email=" + this.email + ")";
        }
    }
}
