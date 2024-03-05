package mongodbexample.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import mongodbexample.models.Student;

public interface StudentRepository extends MongoRepository<Student, Integer> {
	

}
