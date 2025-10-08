package com.example.xdemo.dto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class UserDto {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  @Column(name = "name", length = 50)
  private String name;
  @Column(name = "email", length = 50)
  private String email;

  public UserDto() {
}

// Convenience constructor for creating new entities easily
public UserDto(Long id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
}

// ---------- Getters and Setters ----------

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

// ---------- Optional (useful for debugging / logs) ----------

@Override
public String toString() {
  return "UserDto{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", email='" + email + '\'' +
      '}';
}

}

