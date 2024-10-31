package pl.api.timetracko.Enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;

import static pl.api.timetracko.Enums.Permissions.*;

@RequiredArgsConstructor
public enum WorkspaceRole {
    MEMBER(Set.of(MEMBER_READ, MEMBER_UPDATE, MEMBER_DELETE, MEMBER_CREATE)),
    ADMIN(Set.of(ADMIN_CREATE,ADMIN_DELETE, ADMIN_READ,ADMIN_UPDATE)),
    MANAGER(Set.of(MANAGER_CREATE,MANAGER_DELETE, MANAGER_READ,MANAGER_UPDATE)),;

    @Getter
    private final Set<pl.api.timetracko.Enums.Permissions> permissions;

    public List<SimpleGrantedAuthority> getAuthorities(){
        var authorities= getPermissions()
                .stream()
                .map(permissions -> new SimpleGrantedAuthority(permissions.name()))
                .toList();

        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return authorities;
    }
}
