package fr.sma.bw.constants;

public final class EnumConstants {
	
  public static final String SELECT_JT_FIRST_TEN = "SELECT * from JOURNAL_TECHNIQUE FETCH FIRST 10 ROWS ONLY";
  public static final String INSERT_JT = "INSERT into JOURNAL_TECHNIQUE(LB_OPERATION,TYPE_OPERATION, DT_OPERATION, CD_RETOUR, STATUT) VALUES(#{lbOperation}, #{typeOperation}, #{dateOperation}, #{cdRetour}, #{statut} )";
  public static final String DSNAME = "DataSource";
  public static final String HIKARI_SCHEMA_UNDEFINED = "undefined";
  public static final String HIKARI_SCHEMA_EMPTY = "";
  public static final String SPRING_PROFILE_PRODUCTION = "prod";
  public static final String SERVICE_JOURNAUX_ENDPOINT = "/api/journaux";
  private EnumConstants() {}

}
