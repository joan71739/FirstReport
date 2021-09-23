package build.dao;

public class BuildDAOFactory {
	
	
	
	private static final BuildDAO buildDAO = createBuildDAO();
	
	private static BuildDAO createBuildDAO() {
		BuildDAO bDAO = new BuildDAO();
		return bDAO;
	}
	
	public static BuildDAO getBuildDAO() {
		return buildDAO;
	}

}
