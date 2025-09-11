import requests
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

# Fetch users from API
response = requests.get("http://localhost:8080/users")
data = response.json()

# Convert to data frame for processing
df = pd.DataFrame(data)

print(df.head())
print(f"Total users: {len(df)}")

# Domain analysis
df['domain'] = df['email'].str.split('@').str[1]
domain_counts = df['domain'].value_counts()
print(domain_counts)

sns.countplot(data = df, x = 'domain')
plt.title("User email domains:")
plt.show()

df.to_csv("users_processed.csv", index = False)