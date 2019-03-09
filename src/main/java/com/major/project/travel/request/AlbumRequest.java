package com.major.project.travel.request;

import com.major.project.travel.model.AlbumStatus;
import com.major.project.travel.util.StringListConverter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Convert;
import java.util.List;

/**
 * Created by HUY on 3/5/2019
 **/
public class AlbumRequest {

    private List<String> names;

    private List<String> urlImages;

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<String> getUrlImages() {
        return urlImages;
    }

    public void setUrlImages(List<String> urlImages) {
        this.urlImages = urlImages;
    }
}
