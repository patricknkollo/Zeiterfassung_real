/*
package com.einsatzstunden.demo.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationRole {
  USER(Sets.newHashSet()),
  ADMIN(Sets.newHashSet(ApplicationUserPermission.USER_READ, ApplicationUserPermission.USER_WRITE,
      ApplicationUserPermission.DEV_READ, ApplicationUserPermission.DEV_WRITE,
      ApplicationUserPermission.ADMIN_READ, ApplicationUserPermission.ADMIN_WRITE)),
  DEV(Sets.newHashSet(ApplicationUserPermission.DEV_WRITE, ApplicationUserPermission.DEV_READ));

  private final Set<ApplicationUserPermission> permissionSet;

  ApplicationRole(Set<ApplicationUserPermission> permissionSet){
    this.permissionSet=permissionSet;
  }

  public Set<ApplicationUserPermission> getPermissionSet(){
    return permissionSet;
  }

  public  Set<SimpleGrantedAuthority> getGrantedAuthorities(){
    Set<SimpleGrantedAuthority> permissions = getPermissionSet()
        .stream()
        .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
        .collect(Collectors.toSet());
    permissions.add(new SimpleGrantedAuthority(""+this.name()));
    return permissions;
  }
}
*/
