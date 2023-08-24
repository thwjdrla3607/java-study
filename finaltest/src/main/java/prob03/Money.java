package prob03;

import java.util.Objects;

public class Money {
	private int amount;
	
	/* 코드 작성 */
	public Money(int i) {
		this.amount = i;
	}

	public Money add(Money money) {
		int temp = this.amount;
		this.amount += money.amount;
		Money newMoney = new Money(this.amount);
		this.amount = temp;
		
		return newMoney;
	}

		public Money minus(Money money) {
			int temp = this.amount;
			this.amount -= money.amount;
			Money newMoney = new Money(this.amount);
			this.amount = temp;
			
			return newMoney;
	}

	public Money multiply(Money money) {
		int temp = this.amount;
		this.amount *= money.amount;
		Money newMoney = new Money(this.amount);
		this.amount = temp;
		
		return newMoney;
	}

	public Money devide(Money money) {
		int temp = this.amount;
		this.amount /= money.amount;
		Money newMoney = new Money(this.amount);
		this.amount = temp;
		
		return newMoney;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(amount);
	}

	@Override
	public boolean equals(Object obj) {
		// System.out.println(getClass() + " " + obj.getClass());
		
		if (obj == null)
			return false;
		
		// 인자로 넘어온 Object 객체가 Money 타입인지를 확인 
		if (getClass() == obj.getClass()) {
			Money other = (Money) obj;
			if(amount == other.amount) {
				return true;
			}
		}return false;
	}


}
