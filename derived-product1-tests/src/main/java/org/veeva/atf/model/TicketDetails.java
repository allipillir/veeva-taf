package org.veeva.atf.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Getter
@Setter
@Jacksonized
@NoArgsConstructor
public class TicketDetails {

    private int ticketTotalCount;
    private List<Ticket> ticket;
}
