package app.web.beans;

import app.domain.model.view.HeroViewModel;
import app.service.HeroService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean extends BaseBean {

    private List<HeroViewModel>  models;

    private ModelMapper modelMapper;

    private HeroService heroService;

    public HomeBean() {
    }

    @Inject
    public HomeBean(ModelMapper modelMapper, HeroService heroService) {
        this.modelMapper = modelMapper;
        this.heroService = heroService;
    }

    @PostConstruct
    private void init(){
        this.models = heroService.findAllOrderedByLevel()
                .stream()
                .map(m-> {
                    HeroViewModel viewModel = modelMapper.map(m,HeroViewModel.class);
                    viewModel.setHeroClass(viewModel.getHeroClass().toLowerCase());
                    return viewModel;
                })
                .collect(Collectors.toList());
    }

    public List<HeroViewModel> getModels() {
        return models;
    }

    public void setModels(List<HeroViewModel> models) {
        this.models = models;
    }
}
