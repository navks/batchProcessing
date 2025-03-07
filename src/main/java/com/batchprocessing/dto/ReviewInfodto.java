package com.batchprocessing.dto;

import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Setter
	@Getter
	public class ReviewInfodto implements Serializable{
		/**
		 * 
		 */
		@Serial
		private static final long serialVersionUID = 1L;
		
		
		private Long id;
		
		private String username;
		
		private int ratings;
		
		private String text;
		
		private String email;
		
		private String phoneNumber;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public int getRatings() {
			return ratings;
		}

		public void setRatings(int ratings) {
			this.ratings = ratings;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		
		
}
