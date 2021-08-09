package by.it_academy.jd2.domain.enumeration;

/**
 * Created by Vitali Tsvirko
 */
public enum UserRoles{ //implements GrantedAuthority {
    ROOT("ROOT"),
    ADMIN ("Администратор"),
    USER ("Пользователь"),
    DOCTOR ("Врач"),
    MANAGER ("Административный персонал"),
    ANONYMOUS ("Анонимный пользователь");

    final String roleName;

    UserRoles(String roleName){
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
