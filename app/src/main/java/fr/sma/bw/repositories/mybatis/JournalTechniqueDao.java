package fr.sma.bw.repositories.mybatis;

import fr.sma.bw.constants.EnumConstants;
import fr.sma.bw.entities.JournalTechnique;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.mapping.callback.EntityCallback;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalTechniqueDao extends EntityCallback<JournalTechnique> {

    @Select(EnumConstants.SELECT_JT_FIRST_TEN)
    @Results(value = {
            @Result(property = "idOperation", column = "ID_OPERATION"),
            @Result(property = "lbOperation", column = "LB_OPERATION"),
            @Result(property = "typeOperation", column = "TYPE_OPERATION"),
            @Result(property = "dateOperation", column = "DT_OPERATION"),
            @Result(property = "idCorrelation", column = "ID_CORRELATION"),
            @Result(property = "dateRetourOperation", column = "DT_RETOUR_OPERATION"),
            @Result(property = "statut", column = "STATUT"),
            @Result(property = "idDemande", column = "ID_DEMANDE"),
            @Result(property = "cdRetour", column = "CD_RETOUR"),
            @Result(property = "lbRetour", column = "LB_RETOUR"),
            @Result(property = "fluxEntree", column = "FLUX_ENTREE"),
            @Result(property = "cdApplication", column = "CD_APPLICATION"),
            @Result(property = "fluxRetour", column = "FLUX_RETOUR")

    })
    List<JournalTechnique> getAll();
    
    @Insert(EnumConstants.INSERT_JT)
     Integer save(JournalTechnique jt);

}
