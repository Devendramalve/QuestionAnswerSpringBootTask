package samta.example.samta.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import samta.example.samta.entity.Root;

@Repository
public interface SamtaRepository extends MongoRepository<Root,Integer> {
}
