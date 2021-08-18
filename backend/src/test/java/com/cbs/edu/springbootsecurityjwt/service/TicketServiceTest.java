package com.cbs.edu.springbootsecurityjwt.service;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cbs.edu.springbootsecurityjwt.controller.request.TicketCreationRequest;
import com.cbs.edu.springbootsecurityjwt.model.Priority;
import com.cbs.edu.springbootsecurityjwt.model.Status;
import com.cbs.edu.springbootsecurityjwt.model.Ticket;
import com.cbs.edu.springbootsecurityjwt.model.User;
import com.cbs.edu.springbootsecurityjwt.repository.TicketRepository;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @InjectMocks
    private TicketService ticketService;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private UserService userService;

    @Mock
    private ComponentService componentService;

    @Mock
    private LabelService labelService;

    @Mock
    private WsNotificationService notificationService;

    @Captor
    private ArgumentCaptor<Ticket> ticketArgumentCaptor;

    @BeforeEach
    public void setUp() {
        System.out.println("Set up");
    }

    @Test
    void createTicket() {
        final TicketCreationRequest creationRequest = new TicketCreationRequest();
        creationRequest.setTitle("Some ticket");
        creationRequest.setDescription("Some description");
        creationRequest.setPriority(Priority.BLOCKER);
        creationRequest.setReporterId(11);

        final Integer reporterId = creationRequest.getReporterId();

        final User mockReporter = new User();
        mockReporter.setId(reporterId);
        mockReporter.setFirstName("Yevhenii");
        mockReporter.setLastName("Deineka");

        when(userService.getById(reporterId)).thenReturn(mockReporter);
        when(componentService.getComponentByIds(creationRequest.getComponents())).thenReturn(emptyList());
        when(labelService.getLabelByIds(creationRequest.getLabels())).thenReturn(emptyList());

        ticketService.createTicket(creationRequest);

        verify(ticketRepository, times(1)).save(ticketArgumentCaptor.capture());
        verify(userService, times(1)).getById(reporterId);

        final Ticket savedTicket = ticketArgumentCaptor.getValue();

        Assertions.assertEquals(creationRequest.getTitle(), savedTicket.getTitle(), "Title doesn't match");
        Assertions.assertEquals(creationRequest.getDescription(), savedTicket.getDescription(), "Description doesn't match");
        Assertions.assertEquals(creationRequest.getPriority(), savedTicket.getPriority(), "Priority doesn't match");
        Assertions.assertEquals(Status.OPEN, savedTicket.getStatus(), "Status isn't OPEN");
        Assertions.assertNotNull(savedTicket.getReporter());
        Assertions.assertEquals(reporterId, savedTicket.getReporter().getId(), "Reporter doesn't match");
        Assertions.assertNull(savedTicket.getAssignee());
    }

    @Test
    void getTicket() {
    }
}
