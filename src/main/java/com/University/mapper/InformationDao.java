package com.University.mapper;

import com.University.model.Information;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface InformationDao {
    List<Information> selectInformation();
    Information selectInformationById(Integer informationId);
    List<Information> selectInformationByType(Integer informationType);
}
