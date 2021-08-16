package com.example.future.service;

import com.example.future.core.GenerateQuery;
import com.example.future.filter.FilterRequest;
import com.example.future.model.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final EntityManager entityManager;

    @Override
    public List<User> filterUsers(List<FilterRequest> filterRequests, String filterType) {

        String query = GenerateQuery.getQuery(filterRequests, filterType, "User");
        List<User> users = entityManager.createQuery(query).getResultList();
        return users;
    }
}
