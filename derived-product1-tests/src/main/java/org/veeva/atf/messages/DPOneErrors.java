package org.veeva.atf.messages;

public class DPOneErrors extends AssertionError {

    public static final String TICKET_MENU_ITEM_COUNT = "Verify Ticket menu item count ";

    public static final String TICKET_SLIDES_TITLE = "Verify Slides title in Tickets  menu ";

    public static final String TICKET_SLIDES_TITLE_RESPONSE_TIME = "Verify Slides response time in Tickets  menu ";


    public DPOneErrors(String message, Throwable error){
        super(message,error);
    }

}
