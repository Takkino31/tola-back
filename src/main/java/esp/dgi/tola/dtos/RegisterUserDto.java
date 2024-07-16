package esp.dgi.tola.dtos;

public class RegisterUserDto {
    private String fullName;
    private String firstName;
    private String lastName;
    private String studentNumber;
    private String department;
    private String specialty;
    private String email;
    private String username;
    private String password;
    private String role;  // Ajout du champ role

    // Getters et setters...

    public String getFullName() {
        return fullName;
    }

    public RegisterUserDto setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public RegisterUserDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public RegisterUserDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public RegisterUserDto setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
        return this;
    }

    public String getDepartment() {
        return department;
    }

    public RegisterUserDto setDepartment(String department) {
        this.department = department;
        return this;
    }

    public String getSpecialty() {
        return specialty;
    }

    public RegisterUserDto setSpecialty(String specialty) {
        this.specialty = specialty;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegisterUserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public RegisterUserDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterUserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRole() {
        return role;
    }

    public RegisterUserDto setRole(String role) {
        this.role = role;
        return this;
    }

    @Override
    public String toString() {
        return "RegisterUserDto{" +
                "fullName='" + fullName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", department='" + department + '\'' +
                ", specialty='" + specialty + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
