package org.esun.database.custom.Impl;

import jakarta.persistence.criteria.Predicate;
import org.springframework.context.annotation.Lazy;
import org.esun.database.custom.RegisterRepositoryCustom;
import org.esun.database.entity.User;
import org.esun.database.repository.RegisterRepository;

import java.util.ArrayList;
import java.util.List;

public class RegisterRepositoryCustomImpl implements RegisterRepositoryCustom {

    private final RegisterRepository registerRepository;

    public RegisterRepositoryCustomImpl(@Lazy RegisterRepository registerRepository){
        this.registerRepository = registerRepository;
    }

    @Override
    public List<User> findRegisterBySelective(User user) {
        return registerRepository.findAll((root, query, cb) -> {
            query.distinct(true);
            query.orderBy(cb.asc(root.get("username")));

            final List<Predicate> predicates = new ArrayList<>();
            if (user.getUsername() != null) {
                predicates.add(cb.equal(root.get("username"), user.getUsername()));
            }

            if (user.getRealname() != null) {
                predicates.add(cb.equal(root.get("realname"), user.getRealname()));
            }

            if (user.getPassword() != null) {
                predicates.add(cb.equal(root.get("password"), user.getPassword()));
            }

            if (user.getEmail() != null) {
                predicates.add(cb.equal(root.get("email"), user.getEmail()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
}
