    package dev.yassiraitelghari.domain;

    import jakarta.persistence.*;

    @Entity
    @Table(name = "users")
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name = "username")
        private String username;

        @Column(name = "name")
        private String name;

        @Column(name = "last_name")
        private String lastName ;

        @Column(name = "email")
        private String email;

        @Column(name = "password")
        private String password;

        @Column(name = "role")
        private String role;

        public User(int id, String username,String email ,String name, String password, String role , String lastName) {
            this.id = id;
            this.username = username;
            this.email = email;
            this.name = name;
            this.password = password;
            this.role = role;
            this.lastName = lastName;
        }

        public User(String username,String email,String name, String password, String role, String lastName) {
            this.username = username;
            this.email = email;
            this.name = name;
            this.password = password;
            this.role = role;
            this.lastName = lastName;
        }

        public User() {

        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }