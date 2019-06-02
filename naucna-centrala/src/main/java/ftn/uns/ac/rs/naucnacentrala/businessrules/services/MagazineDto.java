package ftn.uns.ac.rs.naucnacentrala.businessrules.services;

import ftn.uns.ac.rs.naucnacentrala.businessrules.model.Magazine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MagazineDto {

    private Long id;
    private String name;
    private boolean openAccess;

    public MagazineDto(Magazine magazine) {
        this.id = magazine.getId();
        this.name = magazine.getName();
        this.openAccess = magazine.getOpenAccess();
    }
}
