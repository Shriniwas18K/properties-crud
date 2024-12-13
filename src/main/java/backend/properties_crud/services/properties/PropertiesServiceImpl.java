package backend.properties_crud.services.properties;

import org.springframework.beans.factory.annotation.Autowired;
import backend.properties_crud.persistence.properties.PropertyRepository;
import backend.properties_crud.persistence.properties.Property;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
//Services act as bridge between Controllers and Persistence
//NOTE : service layer code should be made independent and lossely
//       coupled with Controllers layer. DTOs in Controller layer
//       should not be imported and given as parameter to any method
//       in service layer. Persistence Layer itself is interfaces
//       so we can import them in service layer as we can change
//       the implementation of persistence anytime but this is not
//       case with DTOs of controllers as they are concrete classes

@Service
public class PropertiesServiceImpl implements PropertiesService{

    private final PropertyRepository propertiesRepository;
    
    @Autowired
    public PropertiesServiceImpl(PropertyRepository propertiesRepository) {
        this.propertiesRepository = propertiesRepository;
    }

    @Override
    public Optional<Property> findById(Long id){
        return propertiesRepository.findById(id);
    }
    
    @Override
    public Long create(String address,String ownerName,
    Float area,Integer rent){
        // here in persistence Property entity we cannot input id field
        // as it is implicitely given by database , hence we need builder 
        // given by lombok , hence we take values in method params
        Property property=Property.builder()
            .address(address)
            .ownerName(ownerName)
            .area(area)
            .rent(rent)
            .build();

        // alternatively we could do same using no args constructor and setters
        property=propertiesRepository.save(property);
        return property.getId();
    }

    @Override
    public List<Property> findAll(){
        return propertiesRepository.findAll();
    }
}