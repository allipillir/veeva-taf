package org.veeva.atf.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Jacksonized
@NoArgsConstructor
public class Ticket {

    private String url;

    private int response;

    private String title;

}
