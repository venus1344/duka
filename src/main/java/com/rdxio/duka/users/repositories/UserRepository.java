/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.rdxio.duka.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.rdxio.duka.users.entities.User;

/**
 *
 * @author 7X
 */
public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByLogin(String login);

}
