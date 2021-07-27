package utp.ts.spoilerroom;

import java.sql.Timestamp;
import utp.ts.spoilerroom.dao.implementations.h2.H2VoteDAO;
import utp.ts.spoilerroom.dto.PostDTO;
import utp.ts.spoilerroom.dto.VoteDTO;
import utp.ts.spoilerroom.models.beans.Post;

public class App
{
	public static void main(String[] args)
	{
		H2VoteDAO voteDAO = new H2VoteDAO();

		VoteDTO voteDTO = new VoteDTO();

		voteDTO.setPostId(1);
		voteDTO.setAccountId(1);
		voteDTO.setVoteType(1);
		voteDTO.setCreationDate(new Timestamp(System.currentTimeMillis()));

		VoteDTO vt = voteDAO.insert(voteDTO);

		System.out.println("PEGA");
	}
}
