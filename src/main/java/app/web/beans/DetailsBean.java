package app.web.beans;

import app.domain.model.service.HeroServiceModel;
import app.domain.model.view.HeroDetailsViewModel;
import app.service.HeroService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class DetailsBean extends BaseBean {

    private HeroDetailsViewModel model;

    private ModelMapper modelMapper;

    private HeroService heroService;

    public DetailsBean() {
    }

    @Inject
    public DetailsBean(ModelMapper modelMapper, HeroService heroService) {
        this.modelMapper = modelMapper;
        this.heroService = heroService;
    }

    @PostConstruct
    private void init(){

        String heroId = getRequestParameterMap().get("id");
        HeroServiceModel serviceModel = heroService.findById(heroId);
        this.model = modelMapper.map(serviceModel,HeroDetailsViewModel.class);
    }

    public HeroDetailsViewModel getModel() {
        return model;
    }

    public void setModel(HeroDetailsViewModel model) {
        this.model = model;
    }
}
