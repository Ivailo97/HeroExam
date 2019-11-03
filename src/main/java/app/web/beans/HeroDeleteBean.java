package app.web.beans;

import app.domain.model.view.HeroDeleteViewModel;
import app.service.HeroService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.Map;

@Named
@RequestScoped
public class HeroDeleteBean extends BaseBean {

    private HeroDeleteViewModel model;

    private ModelMapper modelMapper;

    private HeroService heroService;

    public HeroDeleteBean() {
    }

    @Inject
    public HeroDeleteBean(ModelMapper modelMapper, HeroService heroService) {
        this.modelMapper = modelMapper;
        this.heroService = heroService;
    }

    @PostConstruct
    private void init(){

        String heroId = getRequestParameterMap().get("id");
        this.model = modelMapper.map(heroService.findById(heroId),HeroDeleteViewModel.class);
    }

    public HeroDeleteViewModel getModel() {
        return model;
    }

    public void setModel(HeroDeleteViewModel model) {
        this.model = model;
    }

    public void delete() throws IOException {

        heroService.deleteById(model.getId());

        redirect("home");
    }
}
