package ch3.case01;

public interface LoadBalancer {
	/**
	 * daa
	 * @param candidate
	 */
	void updateCandidate(final Candidate candidate);
	Endpoint nextEndpoint();
}
