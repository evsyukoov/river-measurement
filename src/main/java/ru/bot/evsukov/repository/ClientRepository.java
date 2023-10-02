package ru.bot.evsukov.repository;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;
import ru.bot.evsukov.model.Client;

@Repository
public interface ClientRepository extends KeyValueRepository<Client, String> {

}
