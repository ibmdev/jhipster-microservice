package fr.sma.bw.repositories.mybatis;

import fr.sma.bw.entities.JournalTechnique;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JournalTechniqueDao {

    final String getAllSQL = "SELECT * from JOURNAL_TECHNIQUE FETCH FIRST 10 ROWS ONLY";
    @Select(getAllSQL)
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

}
