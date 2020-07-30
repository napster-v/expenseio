package com.expense.io.project.auth;

import com.expense.io.base.model.AppBaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Avatar extends AppBaseModel {
    private String url;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private AppUser user;

    public Avatar(String url) {
        this.url = url;
    }
}
