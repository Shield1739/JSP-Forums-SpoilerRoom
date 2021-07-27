package utp.ts.spoilerroom.dto;

import utp.ts.spoilerroom.models.beans.Vote;

public class VoteDTO extends Vote
{
	public VoteDTO()
	{
	}

	/**
	 * Beans
	 */

	public Vote getVote()
	{
		Vote vote = new Vote();

		vote.setPostId(this.getPostId());
		vote.setAccountId(this.getAccountId());
		vote.setVoteType(this.getVoteType());
		vote.setCreationDate(this.getCreationDate());

		return vote;
	}
}
