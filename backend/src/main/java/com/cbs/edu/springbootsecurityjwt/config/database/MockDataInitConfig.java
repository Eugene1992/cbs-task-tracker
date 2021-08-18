package com.cbs.edu.springbootsecurityjwt.config.database;

import static java.util.Arrays.asList;

import java.util.Collections;
import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cbs.edu.springbootsecurityjwt.model.Component;
import com.cbs.edu.springbootsecurityjwt.model.Label;
import com.cbs.edu.springbootsecurityjwt.model.Priority;
import com.cbs.edu.springbootsecurityjwt.model.Project;
import com.cbs.edu.springbootsecurityjwt.model.Role;
import com.cbs.edu.springbootsecurityjwt.model.Status;
import com.cbs.edu.springbootsecurityjwt.model.Ticket;
import com.cbs.edu.springbootsecurityjwt.model.TicketType;
import com.cbs.edu.springbootsecurityjwt.model.User;
import com.cbs.edu.springbootsecurityjwt.repository.ComponentRepository;
import com.cbs.edu.springbootsecurityjwt.repository.LabelRepository;
import com.cbs.edu.springbootsecurityjwt.repository.ProjectRepository;
import com.cbs.edu.springbootsecurityjwt.repository.RoleRepository;
import com.cbs.edu.springbootsecurityjwt.repository.TicketRepository;
import com.cbs.edu.springbootsecurityjwt.service.UserService;

@Configuration
public class MockDataInitConfig {

    @Bean
    public CommandLineRunner loadData(RoleRepository roleRepository,
                                      TicketRepository ticketRepository,
                                      ProjectRepository projectRepository,
                                      UserService userService,
                                      LabelRepository labelRepository,
                                      ComponentRepository componentRepository) {
        return (args) -> {
            final Role roleAdmin = roleRepository.save(new Role("ROLE_ADMIN"));
            final Role roleUser = roleRepository.save(new Role("ROLE_USER"));

            final Project atpProject = new Project();
            atpProject.setName("ATP");
            final Project stgProject = new Project();
            stgProject.setName("STG");

            final Project savedAtpProject = projectRepository.save(atpProject);
            final Project savedSTGProject = projectRepository.save(stgProject);

            final User yevheniiUser = userService.saveUser(
                    User.builder()
                            .firstName("Yevhenii")
                            .lastName("Deineka")
                            .username("yede")
                            .password("qwerty")
                            .roles(new HashSet<>(Collections.singletonList(roleAdmin)))
                            .build()
            );

            final User johnUser = userService.saveUser(
                    User.builder()
                            .firstName("John")
                            .lastName("Doe")
                            .username("jodo")
                            .password("qwerty")
                            .roles(new HashSet<>(Collections.singletonList(roleUser)))
                            .build()
            );

            Label buildDefectLabel = labelRepository.save(Label.builder().name("build_defect").build());
            Label phaseOneLabel = labelRepository.save(Label.builder().name("phase_1").build());
            Label gapLabel = labelRepository.save(Label.builder().name("gap").build());


            Component ramComponent = componentRepository.save(Component.builder().name("RAM").build());
            Component securityComponent = componentRepository.save(Component.builder().name("Security").build());
            Component svtComponent = componentRepository.save(Component.builder().name("SVT").build());

            ticketRepository.save(
                    Ticket.builder()
                            .title("Implement RAM service")
                            .description("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium " +
                                    "doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis " +
                                    "et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem " +
                                    "quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores " +
                                    "eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem " +
                                    "ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius " +
                                    "modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.")
                            .key("IJC-1")
                            .priority(Priority.MAJOR)
                            .reporter(yevheniiUser)
                            .project(savedAtpProject)
                            .assignee(johnUser)
                            .status(Status.OPEN)
                            .type(TicketType.STORY)
                            .labels(asList(buildDefectLabel, phaseOneLabel, gapLabel))
                            .components(asList(ramComponent, securityComponent, svtComponent))
                            .build()
            );
        };
    }
}
