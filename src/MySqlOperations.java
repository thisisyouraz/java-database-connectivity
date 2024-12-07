import java.sql.*;
import java.util.Objects;

public class MySqlOperations implements CRUD
{
	Connection con;
	String dbUrl = "jdbc:mysql://localhost:3306/jdbc";
	String dbUser = "root";
	String dbPassword = "root1234";

	@Override
	public int insert(User user)
	{
		Statement statement = null;
		try
		{
			con = getDBConnection();
			statement = con.createStatement();

			String sql = "INSERT INTO users (id, name, email,gender, status)" + "VALUES (" + user.getId() + ",'" + user.getName() + "','"
					+ user.getEmail() + "','" + user.getGender() + "'," + user.isStatus() + ")";

			return statement.executeUpdate(sql);
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
		finally
		{
			try
			{
				if (Objects.nonNull(con))
				{
					con.close();
				}
				if (Objects.nonNull(statement))
				{
					statement.close();
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

	@Override
	public int update(User user)
	{
		Statement statement = null;
		try
		{
			con = getDBConnection();
			statement = con.createStatement();

			String sql = "UPDATE users SET name = '" + user.getName() + "', email = '" + user.getEmail() + "' WHERE id = " + user.getId();

			return statement.executeUpdate(sql);
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if (Objects.nonNull(con))
				{
					con.close();
				}
				if (Objects.nonNull(statement))
				{
					statement.close();
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}

		return 0;
	}

	@Override
	public int delete(User user)
	{
		Statement statement = null;
		try
		{
			con = getDBConnection();
			statement = con.createStatement();
			String sql = "DELETE FROM users WHERE id = " + user.getId();

			return statement.executeUpdate(sql);
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if (Objects.nonNull(con))
				{
					con.close();
				}
				if (Objects.nonNull(statement))
				{
					statement.close();
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}

		return 0;
	}

	@Override
	public void select()
	{
		ResultSet rs = null;
		Statement statement = null;
		try
		{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root1234");
			statement = con.createStatement();

			String sql = "SELECT * FROM users";
			rs = statement.executeQuery(sql);

			while (rs.next())
			{
				System.out.println();
				System.out.print (rs.getInt("id") + " |");
				System.out.print(rs.getString("name")  + " |");
				System.out.print(rs.getString("email")  + " |");
				System.out.print(rs.getString("gender")  + " |");
				System.out.print(rs.getBoolean("status")  + " |");
			}
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if (Objects.nonNull(rs))
				{
					rs.close();
				}
				if (Objects.nonNull(con))
				{
					con.close();
				}
				if (Objects.nonNull(statement))
				{
					statement.close();
				}
			}catch (SQLException ex)
			{
				ex.printStackTrace();
			}
		}
	}

	private Connection getDBConnection() throws SQLException
	{
		return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
	}

}
