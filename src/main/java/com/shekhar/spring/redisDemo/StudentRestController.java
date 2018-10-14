package com.shekhar.spring.redisDemo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shekhar.spring.redisDemo.Student.Gender;

/**
 * @author HP
 *
 */
@RestController
class StudentRestController {
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	RedisListService redisListService;

	@RequestMapping("/getStudentByID/{id}")
	public Student getStudentByID(@PathVariable String id) {
		Optional<Student> optional = this.studentRepository.findById(id);
		return optional.get();
	}

	@RequestMapping("/saveStudent/{name}")
	public Student saveStudent(@PathVariable String name) {
		Student student = new Student("1", name, Gender.MALE, 1);
		return studentRepository.save(student);
	}

	@RequestMapping("/appendInList/{name}/{value}")
	public String appendInList(@PathVariable String name,@PathVariable String value) {
		redisListService.addLink(name, value);
		return "Success";
	}
	@RequestMapping("/listSize/{name}")
	public Long listSize(@PathVariable String name) {
		return redisListService.getSize(name);


	}
}