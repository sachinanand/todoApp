import com.ig.Role
import com.ig.User
import com.ig.UserRole

class BootStrap {

    def init = { servletContext->

        setupRoles()
        if (!User.count()) {
            User user1 = new User(username: "Sachin", password: "abcde", email: "sachin@intelligrape.com").save(failOnError: true)
            new UserRole(user: user1, role: Role.findByAuthority("ROLE_USER")).save(failOnError: true)

            User user2 = new User(username: "Manoj", password: "abcde", email: "manoj@intelligrape.com").save(failOnError: true)
            new UserRole(user: user2, role: Role.findByAuthority("ROLE_USER")).save(failOnError: true)

        }


    }
    def destroy = {
    }

    void setupRoles() {
        if (!Role.count()) {
            ["ROLE_ADMIN", "ROLE_USER"].each { String role ->
                if (!(Role.findByAuthority(role))) {
                    new Role(authority: role).save()
                }
            }
        }
    }
}
