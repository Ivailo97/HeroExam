package app.web.beans;

import app.domain.model.binding.HeroBindingModel;
import app.domain.model.service.HeroServiceModel;
import app.service.HeroService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class HeroCreateBean extends BaseBean{

    private HeroService heroService;

    private HeroBindingModel model;

    private ModelMapper modelMapper;

    public HeroCreateBean() {
    }

    @Inject
    public HeroCreateBean(HeroService heroService, ModelMapper modelMapper) {
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init(){
        this.model = new HeroBindingModel();
    }

    public HeroBindingModel getModel() {
        return model;
    }

    public void setModel(HeroBindingModel model) {
        this.model = model;
    }

    public void create() throws IOException {

        model.setHeroClass(model.getHeroClass().toUpperCase());
        boolean hasCreated = heroService.createHero(modelMapper.map(model, HeroServiceModel.class));

        if (hasCreated){
            redirect("/home");
        }
    }
}
