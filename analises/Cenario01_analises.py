
# coding: utf-8

# In[20]:

import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns


# In[21]:

deputados = pd.read_csv('../../../data/saida_deputados.csv')
inteiros = pd.read_csv('../../../data/saida_int.csv')


# In[22]:

group_dep = deputados.groupby('tam').mean()
group_int = inteiros.groupby('tam').mean()


# In[23]:

plt.plot(group_dep.index,group_dep.duracao, label='Deputados')
plt.plot(group_int.index,group_int.duracao, label='Inteiros')
plt.title('Duração média de Ordenação')
plt.xlabel('Quantidade de dados')
plt.ylabel('Duração [ms]')
plt.legend()
plt.savefig('../../images/cenario01/duracao.png')
plt.show()


# In[24]:

plt.plot(group_dep.index,group_dep.num_comparacao, label='Deputados')
plt.plot(group_int.index,group_int.num_comparacao, label='Inteiros')
plt.title('Número médio de Comparações')
plt.xlabel('Quantidade de dados')
plt.ylabel('Comparações')
plt.yscale('log')
plt.legend()
plt.savefig('../../images/cenario01/comparacao.png')
plt.show()


# In[25]:

plt.plot(group_dep.index,group_dep['num_copia '], label='Deputados')
plt.plot(group_int.index,group_int['num_copia '], label='Inteiros')
plt.title('Número médio de Cópias')
plt.xlabel('Quantidade de dados')
plt.ylabel('Cópias')
plt.yscale('log')
plt.legend()
plt.savefig('../../images/cenario01/copia.png')
plt.show()


# In[ ]:



