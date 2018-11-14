package ar.edu.itba.paw.models;

import javax.persistence.*;

@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admins_adminId_seq")
    @SequenceGenerator(sequenceName = "admins_adminId_seq", name = "admins_adminId_seq", allocationSize = 1)
    @Column(name = "adminId")
    private Integer adminId;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    private User userAdmin;

    public Admin(User userAdmin) {
        this.userAdmin = userAdmin;
    }

    public Admin(){
        //Just for hibernate
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public User getUserAdmin() {
        return userAdmin;
    }

    public void setUserAdmin(User userAdmin) {
        this.userAdmin = userAdmin;
    }
}
