package presidents;

public class President implements Comparable<President>{
	private int number;
	private String firstName;
	private String middleName;
	private String lastName;
	private int startTerm;
	private int endTerm;
	private String party;
	private String funFact;
	private String photo;

	public President(){
	}
	
	public President(int number, String firstName, String middleName, String lastName, int startTerm, int endTerm, String party, String funFact, String photo) {
		this.number = number;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.startTerm = startTerm;
		this.endTerm = endTerm;
		this.party = party;
		setFunFact(funFact);
		setPhoto(photo);
	}

	public int getTermNumber() {
		return number;
	}

	public void setTermNumber(int number) {
		this.number = number;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getStartDate() {
		return startTerm;
	}

	public void setStartDate(int startTerm) {
		this.startTerm = startTerm;
	}

	public int getEndDate() {
		return endTerm;
	}

	public void setEndDate(int endTerm) {
		this.endTerm = endTerm;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}
	
	public String getFunFact(){
		return this.funFact;
	}
	
	public void setFunFact(String funFact){
		this.funFact = funFact;
	}
	
	public String getPhoto(){
		return this.photo;
	}
	
	public void setPhoto(String photo){
		this.photo = photo;
	}
	
    @Override
    public int compareTo(President other) {
        return this.lastName.compareToIgnoreCase(other.lastName);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        President other = (President) obj;
        if (this.lastName == null || this.firstName == null) {
            if (other.lastName != null || other.firstName != null)
                return false;
        }
        else if (!this.lastName.equals(other.lastName)) return false;
        return true;
    }

	@Override
	public String toString() {
		return firstName + " " + middleName + " " + lastName + " " + startTerm + "-" + endTerm + " " + party + " " + funFact;
	}
}
