package agg.deepa.productservices;

import agg.deepa.productservices.inheritanceexample.joinedtable.JTMentorRepository;
import agg.deepa.productservices.inheritanceexample.joinedtable.JTUserRepository;
//import agg.deepa.productservices.inheritanceexample.joinedtable.Mentor;
//import agg.deepa.productservices.inheritanceexample.joinedtable.User;

import agg.deepa.productservices.inheritanceexample.singleclass.Mentor;
import agg.deepa.productservices.inheritanceexample.singleclass.SCMentorRepository;
import agg.deepa.productservices.inheritanceexample.singleclass.SCUserRepository;
import agg.deepa.productservices.inheritanceexample.singleclass.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServicesApplicationTests {
    @Autowired
    private SCUserRepository userRepository;
//    private JTUserRepository userRepository;
    @Autowired
//    private JTMentorRepository mentorRepository;
    private SCMentorRepository mentorRepository;

    @Test
    void contextLoads() {
    }
    @Test
    void testDifferentInheritance(){
        User user = new User();
        user.setEmail("deepa@scaler.com");
        user.setPassword("password");

        userRepository.save(user);

        Mentor mentor = new Mentor();
        mentor.setEmail("dee@gmail.com");
        mentor.setPassword("pass");
        mentor.setNumberOfMentees(4);
        mentor.setNumberOfSessions(50);
        mentorRepository.save(mentor);
    }
}
