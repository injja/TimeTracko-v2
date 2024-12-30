package pl.api.timetracko.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.api.timetracko.models.GroupMember;
import pl.api.timetracko.models.WorkspaceMember;
import pl.api.timetracko.repositories.CrudRepository;
import pl.api.timetracko.repositories.GroupMemberRepository;
import pl.api.timetracko.repositories.GroupRepository;

@Service
public abstract class GroupMemberService<T extends GroupMember> extends CrudService<T> {
    protected CrudRepository<T> crudRepository;
    protected GroupMemberRepository<T> groupMemberRepository;

    @Autowired
    public GroupMemberService(CrudRepository<T> crudRepository, GroupMemberRepository<T> groupMemberRepository) {
        super(crudRepository);
        this.groupMemberRepository = groupMemberRepository;
    }

    public GroupMember desactivate(Long id){
        GroupMember member = findById(id);
        member.setActive(false);
        if(member instanceof WorkspaceMember){
            ((WorkspaceMember) member).getProjectMembers().forEach(projectMember -> projectMember.setActive(false));
        }
        return groupMemberRepository.save(member);
    }
}